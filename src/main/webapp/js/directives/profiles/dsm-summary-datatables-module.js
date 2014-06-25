(function() {
    var dsmSummaryDatatables = sellInNamespace('sellIn.directives.dsmsummarydatatables');

    angular.module('sellIn.directives.dsmsummarydatatables', [
                   'ngRoute',
                   'datatables',
                   'ui.bootstrap',
                   'sellIn.resources.dealerProfile',
                   'sellIn.resources.dealer',
                   'sellIn.routing.paths'])
        .controller('dsmSummaryDatatablesController',dsmSummaryDatatables.DsmSummaryDatatablesController)
        .directive('dsmSummaryDatatables', dsmSummaryDatatables.DsmSummaryDatatablesDirective);
})();