(function () {
    var unsavedChanges = sellInNamespace('sellIn.directives.unsavedChanges');

    function UnsavedChangesController($scope, $modalInstance, dealerProfileResource, orderSegments, profile, role) {

        $scope.saveChanges = function () {

            profile.nonCompliant = !!(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal'));

            var os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};

            if (role.rsm === true) {
                dealerProfileResource.rsmSave(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            } else if (role.dsm === true) {
                dealerProfileResource.dsmSave(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            } else {
                dealerProfileResource.save(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos);
                    });
            }
        };

        $scope.exitWithNoChanges = function () {
            var profile = {successful: true, message: 'no changes saved'};
            $modalInstance.close(profile);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    unsavedChanges.UnsavedChangesController = UnsavedChangesController;
})();