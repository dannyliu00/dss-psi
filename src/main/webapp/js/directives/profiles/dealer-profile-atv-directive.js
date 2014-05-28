(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function ATVProfileDetailsDirective() {
        return {
            restrict: 'E',
            templateUrl: 'js/directives/profiles/atv-template.html'
        };
    }

    dealerProfiles.ATVProfileDetailsDirective = ATVProfileDetailsDirective;
})();