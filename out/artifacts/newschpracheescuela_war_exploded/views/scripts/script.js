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
                .when('/contact',
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
             $scope.slideShow = function () {
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
                 $timeout($scope.slideShow, 3000);
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
                $scope.slideShow();
            });

        }
    ])

    .controller('BackendController',
    [
        '$scope', '$http' ,function ($scope, $http)
        {

          /*  $http.get('/users').success(function(data) {
                $scope.users = data;
            });*/

            $http({
                method: 'GET',
                url: '/users'
            }).then(function successCallback(response) {
                $scope.users = response.data;
                // this callback will be called asynchronously
                // when the response is available
            }, function errorCallback(response) {
                alert("код : " +response.status);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        }
    ]);
