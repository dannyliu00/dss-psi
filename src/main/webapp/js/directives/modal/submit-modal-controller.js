(function () {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    function SubmitController($scope, $modalInstance, dealerProfileResource, orderSegments, profile, level, confirm) {

        $scope.submit = function (os) {

            profile.nonCompliant = !!(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal'));

            os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};

            if (level === "dealer") {
                for (var i = 0; i < orderSegments.length; i++) {
                    orderSegments[i].dealerEmail = confirm;
                }

                os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};

                dealerProfileResource.submit(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos);
                    });
            } else if (level === "dsm") {
                dealerProfileResource.approveRequested(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos);
                    });
            }
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    submitValues.SubmitController = SubmitController;
})();