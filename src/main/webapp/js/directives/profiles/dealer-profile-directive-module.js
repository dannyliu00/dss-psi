(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    angular.module('sellIn.directives.profiles', ['ngRoute', 'datatables', 'ui.bootstrap'])
        .controller('dealerProfileDirectiveController', dealerProfiles.DealerProfileDirectiveController)
        .directive('profileDetails', dealerProfiles.ProfileDetailsDirective);

})();