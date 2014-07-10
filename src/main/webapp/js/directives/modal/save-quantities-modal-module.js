(function() {
    var saveQuantities = sellInNamespace('sellIn.directives.savequantities');

    angular.module('sellIn.directives.savequantities', ['ngRoute', 'ui.bootstrap', 'sellIn.resources.dealerProfile'])
        .controller('saveQuantitiesController', saveQuantities.SaveQuantitiesController)
    ;
})();