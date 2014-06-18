(function() {
    var dsmSummary = sellInNamespace('sellIn.pages.dsmsummary');

    angular.module('sellIn.pages.dsmsummary', ['sellIn.routing.paths'])
        .controller('dsmSummaryController', dsmSummary.DsmSummaryController);
})();