(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function MotorcycleProfileDetailsDirective() {
        return {
            restrict: 'E',
            controller: dealerProfiles.DealerProfileDirectiveController,
            templateUrl: 'js/directives/profiles/motorcycle-template.html'
        };
    }

    dealerProfiles.MotorcycleProfileDetailsDirective = MotorcycleProfileDetailsDirective;
})();