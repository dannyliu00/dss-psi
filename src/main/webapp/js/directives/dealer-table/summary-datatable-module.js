(function() {
    var summaryDatatables = sellInNamespace('sellIn.directives.summarydatatables');

    angular.module('sellIn.directives.summarydatatables', [
                   'ngRoute',
                   'datatables',
                   'ui.bootstrap',
                   'sellIn.resources.dealerProfile',
                   'sellIn.resources.dealer',
                   'sellIn.routing.paths'])
        .controller('summaryDatatablesController',summaryDatatables.SummaryDatatablesController)
        .directive('summaryDatatables', summaryDatatables.SummaryDatatablesDirective);
})();