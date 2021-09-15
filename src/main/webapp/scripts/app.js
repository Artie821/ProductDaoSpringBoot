$(document).ready(function() {
    $('#table').DataTable( {
        "language": {
            "lengthMenu": "Wyświetl _MENU_ rekordów na stronę",
            "zeroRecords": "Nic nie znaleziono :(",
            "info": "Wyświetlam stronę _PAGE_ z _PAGES_",
            "infoEmpty": "Brak dostępnych rekordów",
            "search":         "Szukaj:",
            "paginate": {
                "first":      "Pierwsza",
                "last":       "Ostatnia",
                "next":       "Następna",
                "previous":   "Poprzednia"
            },
            "infoFiltered": "(przefiltorwano z _MAX_ rekordów)"
        },
    },
    {
        rowReorder: {
            selector: 'td:nth-child(2)'
        },
        responsive: true
    }
    );
} );

function goBack() {
    window.history.back();
}

$(function() {
    $('#exampleModal').on('show.bs.modal', function(event) {
        let button = $(event.relatedTarget) // Button that triggered the modal
        let value = button.data('value')
        let object = button.data('object')// Extract info from data-* attributes
        let buttonYes = document.getElementById("btnname")
        buttonYes.onclick= (ev) => {
            ev.preventDefault();
            let formSubmitId = document.getElementById("formSubmitId");
            formSubmitId.submit();
            console.log(value);
            console.log(object);
        }

    });
});
