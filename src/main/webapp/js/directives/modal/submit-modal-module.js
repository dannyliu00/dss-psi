(function() {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    angular.module('sellIn.directives.submitValues', ['ngRoute', 'ui.bootstrap'])
        .controller('submitController', submitValues.SubmitController)
    ;
})();