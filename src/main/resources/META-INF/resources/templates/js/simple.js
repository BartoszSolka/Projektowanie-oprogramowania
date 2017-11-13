/**
 * Created by Paweł on 13.11.2017.
 */
var dodajZlecenie = function () {
    var tytulZlecenia = document.getElementById('tytulZlecenia').value;
    var cenaZlecenia = document.getElementById('cenaZlecenia').value;
    var czasZlecenia = document.getElementById('czasZlecenia').value;
    var opisZlecenia = document.getElementById('opisZlecenia').value;

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/serviceProvider/4/services',
        dataType: 'json',
        data: {
            id: 4,
            title: tytulZlecenia,
            description: opisZlecenia,
            price: cenaZlecenia,
            estimatedRealisationTime: czasZlecenia
        }
    });
};