/**
 * Created by Ника Тихоновец on 30.04.2017.
 */


nseApp.controller('IndexController', IndexController);

function IndexController($scope, IndexService, USER_ROLES, AuthService) {

    $scope.currentUser = null;
    $scope.userRoles = USER_ROLES;
    $scope.isAuthorized = AuthService.isAuthorized;

    $scope.slides =IndexService.getImagesSlide();
    $scope.choices = IndexService.getChoices();
    $scope.numbers = IndexService.getNumberChoice();

    $("li.header__item:contains('О нас')").addClass('header__item--active');

    $scope.changeTypeChoice = function (event, optionIndex) {
        $(".container__text").parent().find('.container__text').removeClass("container__text--active");
        $("#" + optionIndex).addClass("container__text--active");
        $(".container__number").parent().find('.container__number').removeClass("container__number--active");
        $(event.target).addClass("container__number--active");
    };
}