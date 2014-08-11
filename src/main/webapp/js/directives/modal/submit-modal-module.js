(function () {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    angular.module('sellIn.directives.submitValues', ['ngRoute', 'ui.bootstrap', 'sellIn.resources.dealerProfile'])
        .controller('submitController', submitValues.SubmitController)
    ;
})();