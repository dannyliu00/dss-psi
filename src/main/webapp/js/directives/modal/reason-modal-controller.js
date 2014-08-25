(function () {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');

    function ReasonModalController($scope, $modalInstance, reasonCodeResource, appRoleResource, dealerProfileResource, orderSegments, profile, caption, confirm,translationPSI) {

        var comments = "";
        var authorizationRoleId = 0;
        
        $scope.isCompliant = function() {
        	profile.nonCompliant = !!(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal'));
        	return profile.nonCompliant;
        };
        
        appRoleResource.get().then(function (returnedRole) {
            $scope.role = returnedRole;
            authorizationRoleId = {roleId: returnedRole.authorizationRoleId};
            reasonCodeResource.query(authorizationRoleId)
                .then(function (returnedReasonCodes) {
                    $scope.reasonCodes = returnedReasonCodes;
                });
        });

        $scope.saveChanges = function (id) {

            for (var i = 0, j = orderSegments.length; i < j; i++) {
                var item = orderSegments[i];

                if ($scope.role.rsm === true) {
                    item.adminReasonCode = id;
                    item.adminComments = this.reasonComments;
                } else if ($scope.role.dsm === true) {
                    item.dsmReasonCode = id;
                    item.dsmComments = this.reasonComments;
                } else {
                    item.reasonCode = id;
                    item.dealerComments = this.reasonComments;
                    item.dealerEmail = confirm;
                }
            }

            var os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};

            if (caption === translationPSI.getString('dealerReason')) {
                dealerProfileResource.submit(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            } else if (caption === translationPSI.getString("Submit for Exception")) {
                dealerProfileResource.submitException(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            } else if (caption === translationPSI.getString("Approve with Changes")) {
                dealerProfileResource.approveWChanges(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            } else if (caption === translationPSI.getString("sendBack")) {
                dealerProfileResource.sendBack(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            } else if (caption === translationPSI.getString("Return to DSM")) {
                dealerProfileResource.returnDsm(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            } else if (caption === translationPSI.getString("Approve as Compliant")) {
                dealerProfileResource.compliant(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            } else if (caption === translationPSI.getString("Approve as Non-Compliant")) {
                dealerProfileResource.nonCompliant(os)
                    .then(function (returnedos) {
                        $modalInstance.close(returnedos.successful);
                    });
            }
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    reasonModal.ReasonModalController = ReasonModalController;
})();