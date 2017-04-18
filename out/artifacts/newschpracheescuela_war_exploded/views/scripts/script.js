angular.module('NSE',['ngRoute'])
    .config([
        '$locationProvider','$routeProvider',function ($locationProvider,$routeProvider) {
            $routeProvider
                .when('/index',
                    {
                        templateUrl:'/resources/html/index.html',
                        controller:'IndexController'
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
        '$scope',function ($scope) {
            $scope.slides = [
                {image: 'resources/images/banner-italy.jpg'},
                {image: 'resources/images/banner-london.jpg'},
                {image: 'resources/images/banner-spain.png'},
                {image: 'resources/images/banner-paris.jpg'}
            ];

            $scope.choices = [
                {id:'1', title: 'Удобная запись на курсы', content: 'Lkkfkg'},
                {id:'2', title: 'Возможность общения с носителями языка', content: 'KJkjhkfsk'},
                {id:'3', title: 'Индивидуальный подход к каждому студенту', content: 'KLjcjjjjcc'},
                {id:'4', title: 'Лучшие преподаватели', content: 'KLkjcfhcfuu'},
                {id:'5', title: 'Осуществляется набор в группы с разными уровнями подготовки', content: 'Lkxjjkdcdfhcfhucf'}
            ];
        }
    ]);