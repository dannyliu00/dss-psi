(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    angular.module('sellIn.pages.nondealersummary', [
        'ngRoute',
        'sellIn.routing.paths',
        'sellIn.directives.nondealersummaries',
        'sellIn.resources.rsmProfiles',
        'sellIn.resources.dsmProfiles',
        'sellIn.services.lasttab',
        'sellIn.resources.role',
        'blockUI'])
        .controller('nonDealerSummaryController', nonDealerSummary.NonDealerSummaryController)
        .constant('productTabs',[{name: 'ATV', content: '2', isActive: ''},
                                 {name: 'Ranger', content: '6', isActive: ''},
                                 {name: 'RZR', content: 'Z', isActive: ''},
                                 {name: 'Victory', content: '5', isActive: ''},
                                 {name: 'Indian', content: 'F', isActive: ''}]);
})();