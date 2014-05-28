(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    angular.module('sellIn.directives.profiles', ['datatables'])
        .controller('dealerProfileDirectiveController', dealerProfiles.DealerProfileDirectiveController)
        .directive('motorcycleProfileDetails', dealerProfiles.MotorcycleProfileDetailsDirective)
        .directive('atvProfileDetails', dealerProfiles.ATVProfileDetailsDirective);

})();