/**
 * Created by Ника Тихоновец on 17.04.2017.
 */
function openChoice(event,optionIndex) {
    var i;
    var x = document.getElementsByClassName("container__text");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(optionIndex).style.display = "block";
    if (event !== undefined) {
        var optionNumber = document.getElementsByClassName("container__number");
        for (i = 0; i < optionNumber.length; i++) {
            optionNumber[i].className = optionNumber[i].className.replace(" container__number--active", "");
        }
        event.currentTarget.className += " container__number--active";
    }
}