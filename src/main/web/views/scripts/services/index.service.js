/**
 * Created by Ника Тихоновец on 30.04.2017.
 */

nseApp.service('IndexService', IndexService);

function IndexService() {

    var slides = [
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

    var choices = [
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

    var numbers = [
        {id: '1', classes: 'container__number container__number--active'},
        {id: '2', classes: 'container__number'},
        {id: '3', classes: 'container__number'},
        {id: '4', classes: 'container__number'},
        {id: '5', classes: 'container__number'},
    ];

    this.getImagesSlide = function () {
        return slides;
    };

    this.getChoices = function () {
        return choices;
    };

    this.getNumberChoice = function () {
        return numbers;
    }

}