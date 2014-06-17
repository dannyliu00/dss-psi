(function() {
    var dsmSummary = sellInNamespace('sellIn.pages.dsmsummary');

    angular.module('sellIn.pages.dsmsummary', ['sellIn.directives.producttabs'])
        .controller('dsmSummaryController', dsmSummary.DsmSummaryController);
})();