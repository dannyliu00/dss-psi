(function() {
    var saveQuantities = sellInNamespace('sellIn.directives.savequantities');

    angular.module('sellIn.directives.savequantities', ['ngRoute', 'ui.bootstrap'])
        .controller('saveQuantitiesController', saveQuantities.SaveQuantitiesController)
    ;
})();