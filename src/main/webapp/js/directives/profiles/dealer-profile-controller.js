(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder) {
        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(10)
            .withBootstrap();

        $scope.$watch($scope.orderSegments, function(orderSegments) {

        });
    }

    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();