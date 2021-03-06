(function () {
    var saveQuantities = sellInNamespace('sellIn.directives.savequantities');

    function SaveQuantitiesController($scope, $modalInstance, dealerProfileResource, orderSegments, profile, resetChanges) {

        $scope.saveChanges = function () {

            profile.nonCompliant = !!(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal'));

            var os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};

            dealerProfileResource.save(os)
                .then(function (returnedos) {

                    resetChanges();

                    $modalInstance.close(returnedos);
                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    saveQuantities.SaveQuantitiesController = SaveQuantitiesController;
})();