(function() {
    var unsavedChanges = sellInNamespace('sellIn.directives.unsavedChanges');

    function UnsavedChangesController($scope, $modalInstance, dealerProfileResource, orderSegments, profile, role) {
    	
        $scope.saveChanges = function () {

        	if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
                profile.nonCompliant = true;
            } else {
                profile.nonCompliant = false;
            }
        	
        	var os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};
        	
        	if(role.dsm === true) {
        		dealerProfileResource.dsmSave(os)
                    .then(function(returnedos) {
                        orderSegments = returnedos.orderSegments;
                        profile.nonCompliant = returnedos.nonCompliant;
                        $modalInstance.close();
                });
        	} else {
                dealerProfileResource.save(os)
                    .then(function(returnedos) {
                        orderSegments = returnedos.orderSegments;
                        profile.nonCompliant = returnedos.nonCompliant;
                        $modalInstance.close();
                });
        	}
        };

        $scope.exitWithNoChanges = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    unsavedChanges.UnsavedChangesController = UnsavedChangesController;
})();