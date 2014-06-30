(function() {
    var nonDealerSummaries = sellInNamespace('sellIn.directives.nondealersummaries');

    angular.module('sellIn.directives.nondealersummaries', [
                   'ngRoute',
                   'datatables',
                   'ui.bootstrap',
                   'sellIn.resources.role',
                   'sellIn.resources.dsmProfiles',
                   'sellIn.routing.paths'])
        .controller('nonDealerSummariesController',nonDealerSummaries.NonDealerSummariesController)
        .directive('nonDealerSummariesDirective', nonDealerSummaries.NonDealerSummariesDirective);
})();