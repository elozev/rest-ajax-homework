/**
 * Created by emil on 11/16/16.
 */

var currentPageLoaded = 0;
var isGettingRequest = true;

function addCar() {
    $.ajax({
        url: "http://localhost:8080/rest-ajax-homework/api/cars",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            manufacturer: $('#inputManufacturer').val(),
            model: $('#inputModel').val(),
            engineType : $('#engineType').val(),
            year : $('#yearSelect').val()
        }),
        success: function (newCar) {
            loadIntoTable(newCar);
        },
        error:function () {
            alert("Such car already exists");
        },
        complete: function () {
            $('#inputManufacturer').val("");
            $('#inputModel').val("");
            $('#yearSelect').val("");
            $('#engineType').val("");
        }
    })
}


function loadIntoTable( newCar) {
    var tr = $('<tr>');
    tr.append('<td>' + newCar.manufacturer + '</td>');
    tr.append('<td>' + newCar.model + '</td>');
    tr.append('<td>' + newCar.engineType + '</td>');
    tr.append('<td>' + newCar.year + '</td>');
    tr.append('</td>')
    $('#carsTable').append(tr);
}

function loadCarsFromApi() {
    $.ajax({
        url: "http://localhost:8080/rest-ajax-homework/api/cars",
        type: "GET",
        dataType: "json",
        data:{
            "page" : currentPageLoaded++
        },
        success: function (data) {
            $.each(data, function (index) {
                loadIntoTable(data[index]);
            })
            isGettingRequest = true;
        },
        error: function () {
            $('#noMoreToLoad').empty();
            var paragraph = $('<p>');
            paragraph.append('<b>No more cars to load<b>');
            paragraph.append('</p>');
            $('#noMoreToLoad').append(paragraph);

        }
    })
}

$(document).ready(function () {

    loadCarsFromApi();

    $('form').submit(function (e) {
        e.preventDefault();
        if (!$('#inputManufacturer').val()
            || !$('#inputModel').val()
            || !$('#engineType').val()
            || !$('#yearSelect').val()) {

            alert("All fields required!");
        } else {
            addCar();
        }
    })
});

$(window).scroll(function () {
    if ($(window).scrollTop() + $(window).height() >= $('body').height() && isGettingRequest){
        isGettingRequest = false;
        loadCarsFromApi();
    }
})




