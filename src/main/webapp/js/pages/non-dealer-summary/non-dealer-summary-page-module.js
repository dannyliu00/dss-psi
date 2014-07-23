(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    angular.module('sellIn.pages.nondealersummary', [
        'ngRoute',
        'sellIn.routing.paths',
        'sellIn.directives.nondealersummaries',
        'sellIn.resources.rsmProfiles',
        'sellIn.resources.dsmProfiles',
        'sellIn.services.lasttab',
        'sellIn.resources.role'])
        .controller('nonDealerSummaryController', nonDealerSummary.NonDealerSummaryController)
        .constant('productTabs',[{name: 'ATV', content: '2'},
                                 {name: 'Ranger', content: '6'},
                                 {name: 'RZR', content: 'Z'},
                                 {name: 'Victory', content: '5'},
                                 {name: 'Indian', content: 'F'}]);
})();