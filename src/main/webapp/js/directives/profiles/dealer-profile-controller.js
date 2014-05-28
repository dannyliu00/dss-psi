(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder) {
        $scope.dtOptions = DTOptionsBuilder.newOptions().withPaginationType('full_numbers').withDisplayLength(10);
    }

    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();