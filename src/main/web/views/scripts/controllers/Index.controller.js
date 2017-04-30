/**
 * Created by Ника Тихоновец on 30.04.2017.
 */


angular.module('NSE').controller('IndexController',
    [
        '$scope', '$timeout' ,function ($scope, $timeout, $templateCache) {

        $scope.$on('$routeChangeStart', function(event, next, current) {
            if (typeof(current) !== 'undefined'){
                $templateCache.remove(next.templateUrl);
            }
        });

        $scope.slides = [
            {
                image: 'resources/images/banner-italy.jpg',
                classes: 'banner__slide banner__slide--active'
            },
            {
                image: 'resources/images/banner-london.jpg',
                classes: 'banner__slide'
            },
            {
                image: 'resources/images/banner-spain.png',
                classes: 'banner__slide'
            },
            {
                image: 'resources/images/banner-paris.jpg',
                classes: 'banner__slide'
            }
        ];

        $scope.choices = [
            {
                id: '1',
                title: 'Удобная запись на курсы',
                content: 'Лалалалала лалалала лалаллалалал аллааалла лалаалалал алаллалалла алаллалаа алл',
                classes: 'container__text container__text--active'
            },
            {
                id: '2',
                title: 'Возможность общения с носителями языка',
                content: 'Лалалала лалалаллала аллалаллалла лаллалаллала аллалал',
                classes: 'container__text'
            },
            {
                id: '3',
                title: 'Индивидуальный подход к каждому студенту',
                content: 'Лалалал алалалал алалал алаллала алалаллала аллала',
                classes: 'container__text'
            },
            {
                id: '4',
                title: 'Лучшие преподаватели',
                content: 'Ла лалалалала ллалалалал аллааллала алаллаллалалалал алаллалаллал лал',
                classes: 'container__text'
            },
            {
                id: '5',
                title: 'Осуществляется набор в группы с разными уровнями подготовки',
                content: 'Лалалалал лалалаал ллалалалалалаллал лаллаллалаллаллал лаллалаллалаллала лалаллаллалалал аллаллал',
                classes: 'container__text'
            }
        ];

        $scope.numbers = [
            { id: '1', classes: 'container__number container__number--active'},
            { id: '2', classes: 'container__number'},
            { id: '3', classes: 'container__number'},
            { id: '4', classes: 'container__number'},
            { id: '5', classes: 'container__number'},
        ];

       /* $scope.slideIndex = 0;
        var slideShow = function () {
            var i;
            var x = $(".banner__slide");
            $scope.slideIndex++;
            if ($scope.slideIndex > x.length) {
                $scope.slideIndex = 1
            }
            x[$scope.slideIndex - 1].addClass = "banner__slide--active";
            $timeout(slideShow, 3000);
        };*/

        $scope.changeTypeChoice = function (event,optionIndex) {
            $(".container__text").parent().find('.container__text').removeClass("container__text--active");
            $("#"+ optionIndex).addClass("container__text--active");
            $(".container__number").parent().find('.container__number').removeClass("container__number--active");
            $(event.target).addClass("container__number--active");
        };


    }
    ]);
