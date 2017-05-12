angular.module('NSE',['ngRoute', 'ngMap'])
    .config([
        '$locationProvider','$routeProvider',function ($locationProvider,$routeProvider) {
            $routeProvider
                .when('/index',
                    {
                        templateUrl:'/resources/html/index.html',
                        controller:'IndexController'
                    }
                )
                .when('/contacts',
                    {
                        templateUrl:'/resources/html/contact.html'
                    }
                )
                .when('/backend',
                    {
                        templateUrl:'/resources/html/backend.html',
                        controller:'BackendController'
                    }
                )
                .otherwise({
                    redirectTo:"/index"
                });
            $locationProvider.html5Mode(true);
        }
    ])
    .controller('IndexController',
    [
        '$scope', '$timeout' ,function ($scope, $timeout, $templateCache) {

            $scope.$on('$routeChangeStart', function(event, next, current) {
                if (typeof(current) !== 'undefined'){
                    $templateCache.remove(next.templateUrl);
                }
            });

            $scope.slides = [
                {image: 'resources/images/banner-italy.jpg'},
                {image: 'resources/images/banner-london.jpg'},
                {image: 'resources/images/banner-spain.png'},
                {image: 'resources/images/banner-paris.jpg'}
            ];

            $scope.choices = [
                {id: '1', title: 'Удобная запись на курсы', content: 'Лалалалала лалалала лалаллалалал аллааалла лалаалалал алаллалалла алаллалаа алл'},
                {id: '2', title: 'Возможность общения с носителями языка', content: 'Лалалала лалалаллала аллалаллалла лаллалаллала аллалал'},
                {id: '3', title: 'Индивидуальный подход к каждому студенту', content: 'Лалалал алалалал алалал алаллала алалаллала аллала'},
                {id: '4', title: 'Лучшие преподаватели', content: 'Ла лалалалала ллалалалал аллааллала алаллаллалалалал алаллалаллал лал'},
                {
                    id: '5',
                    title: 'Осуществляется набор в группы с разными уровнями подготовки',
                    content: 'Лалалалал лалалаал ллалалалалалаллал лаллаллалаллаллал лаллалаллалаллала лалаллаллалалал аллаллал'
                }
            ];


             $scope.slideIndex = 0;
                 var slideShow = function () {
                 var i;
                 var x = document.getElementsByClassName("banner__slide");
                 for (i = 0; i < x.length; i++) {
                     x[i].style.display = "none";
                 }
                 $scope.slideIndex++;
                 if ($scope.slideIndex > x.length) {
                     $scope.slideIndex = 1
                 }
                 x[$scope.slideIndex - 1].style.display = "block";
                 $timeout(slideShow, 3000);
             };

            $scope.changeTypeChoice = function (event,optionIndex) {
                var i;
                var x = document.getElementsByClassName("container__text");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                document.getElementById(optionIndex).style.display = "block";
                var optionNumber = document.getElementsByClassName("container__number");
                if (event !== undefined) {
                    for (i = 0; i < optionNumber.length; i++) {
                        optionNumber[i].className = optionNumber[i].className.replace(" container__number--active", "");
                    }
                    event.currentTarget.className += " container__number--active";
                }else{
                    optionNumber[0].className += " container__number--active";
                }
            };

            $(window).load(function(){
                $scope.changeTypeChoice(undefined,1);
                slideShow();
            });

        }
    ])

    .controller('BackendController',
    [
        '$scope', '$http',function ($scope, $http)
        {
            $scope.base_url = "/users";

            $scope.initData = function (event,url) {
                $scope.base_url = url;
                $(".menu__list .menu__item").parent().find('.menu__item').removeClass("menu__item--active");
                $(event.target).parent().addClass("menu__item--active");
                getAllUsers();
            };

            var getAllUsers =  function(){$http({
                    method: 'GET',
                    url: $scope.base_url
                }).then(function successCallback(response) {
                    if (response.status == 200){
                        $scope.data = response.data;
                        $scope.dataKeys = getKeys($scope.data[0]);

                        $scope.formKeys = $scope.dataKeys.slice();
                        $scope.formKeys.splice(0,1);
                    }else{
                        alert("Ошибка сервера!");
                    }
                }, function errorCallback(response) {
                    alert("Error : " +response.status);
                })};

            getAllUsers();
            $scope.deleteItem = function (item) {
                $scope.key = $scope.dataKeys[0].toString();
                $scope.id = item[$scope.key];
                $http({
                    method: 'DELETE',
                    url: $scope.base_url + '/' + $scope.id
                }).then(function successCallback(response) {
                    if (response.status == 200)
                    {
                        getAllUsers();
                    }else{
                        alert("Ошибка сервера");
                    }
                }, function errorCallback(response) {
                    alert("Error : " +response.status);
                });
            };

            $scope.Item = {};
            $scope.addItem = function () {
                if($scope.addForm.$valid) {
                    $http({
                        method: 'POST',
                        url: $scope.base_url,
                        data: $scope.Item["formKeys"]
                    }).then(function successCallback(response) {
                        if (response.status == 200) {
                            $scope.hidePopup('addForm', '.popup__add');
                            getAllUsers();
                        } else {
                            $scope.errorMessage = "Произошла ошибка добавления. Проверьте введенные данные! И попробуйте снова.";
                        }
                    }, function errorCallback(response) {
                        $scope.errorMessage = "Произошла ошибка добавления. Проверьте введенные данные! И попробуйте снова.";
                    });
                }else{
                    $scope.errorMessage = "Заполните все поля!";
                }
            };

            $scope.Item = {};
            $scope.editItemPopup = function (popup, item) {
                $scope.edit = item;
                $scope.Item.formKeys = item;
                $scope.showPopup(popup);
            };

            $scope.editItem = function () {
                if($scope.editForm.$valid){
                    $scope.key = $scope.dataKeys[0].toString();
                    $scope.id = $scope.edit[$scope.key];
                    $http({
                        method: 'PUT',
                        url: $scope.base_url + "/" + $scope.id,
                        data: $scope.Item["formKeys"]
                    }).then(function successCallback(response) {
                        if (response.status == 200){
                            $scope.hidePopup('editForm','.popup__edit');
                            getAllUsers();
                        }else{
                            $scope.errorMessage = "Произошла ошибка редактирования. Проверьте введенные данные! И попробуйте снова.";
                        }
                    }, function errorCallback(response) {
                        $scope.errorMessage = "Произошла ошибка редактирования. Проверьте введенные данные! И попробуйте снова.";
                    });
                }else {
                    $scope.errorMessage = "Заполните все поля!";
                }
            };

            var getKeys = function(obj){
                var keys = [];
                for(var key in obj){
                    keys.push(key);
                }
                return keys;
            };
            
            $scope.hidePopup = function (name, popup) {
               // document.forms[name].reset();
                $(popup).hide();
                $(".popup__bg").hide();
                $scope.Item = {};
            };

            $scope.showPopup = function (popup) {
                $scope.errorMessage = "";
                $(popup).show();
                $(".popup__bg").show();
                $("body").animate({"scrollTop":0},"slow");
            };
        }
    ]);
