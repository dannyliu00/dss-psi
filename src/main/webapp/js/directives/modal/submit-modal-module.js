(function() {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    angular.module('sellIn.directives.submitValues', ['ngRoute', 'ui.bootstrap', 'sellIn.resources.dealerProfileDetails'])
        .controller('submitController', submitValues.SubmitController)
    ;
})();