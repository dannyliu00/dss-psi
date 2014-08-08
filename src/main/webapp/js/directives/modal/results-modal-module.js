(function() {
    var resultsModal = sellInNamespace('sellIn.directives.resultsmodal');

    angular.module('sellIn.directives.resultsmodal', ['ngRoute', 'ui.bootstrap'])
        .controller('resultsModalController', resultsModal.ResultsModalController)
    ;
})();