(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    angular.module('sellIn.directives.profiles', ['datatables'])
        .controller('dealerProfileDirectiveController', dealerProfiles.DealerProfileDirectiveController)
        .directive('profileDetails', dealerProfiles.ProfileDetailsDirective);

})();