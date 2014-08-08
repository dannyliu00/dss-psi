(function () {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    angular.module('sellIn.directives.profiles', [
        'ngRoute',
        'datatables',
        'ui.bootstrap',
        'sellIn.resources.dealerProfile',
        'sellIn.resources.dealer',
        'sellIn.routing.paths',
        'sellIn.resources.role',
        'sellIn.services.lasttab',
        'sellIn.services.currentdealer',
        'blockUI'])
        .controller('dealerProfileDirectiveController', dealerProfiles.DealerProfileDirectiveController)
        .directive('profileDetails', dealerProfiles.ProfileDetailsDirective);
})();