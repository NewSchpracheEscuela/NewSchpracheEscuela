/**
 * Created by Ника Тихоновец on 17.04.2017.
 */
var slideIndex = 0;
slideShow();

function slideShow() {
    var i;
    var x = document.getElementsByClassName("banner__slide");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > x.length) {slideIndex = 1}
    x[slideIndex-1].style.display = "block";
    setTimeout(slideShow, 3000);
}