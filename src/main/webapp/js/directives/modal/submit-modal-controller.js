(function() {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    function SubmitController($scope, $modalInstance, dealerProfileResource, orderSegments, profile) {
    	
        $scope.submit = function (os) {
        	
        	if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
	            profile.nonCompliant = true;
	        } else {
	            profile.nonCompliant = false;
	        }
	    	
	    	var os = {nonCompliant: profile.nonCompliant, orderSegments: orderSegments};
	    	
            dealerProfileResource.submit(os)
                .then(function(returnedOs) {
                	profile.nonCompliant = returnedOs.nonCompliant;
                	profile.orderSegments = returnedOs.orderSegments;
                    $modalInstance.close();
                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    submitValues.SubmitController = SubmitController;
})();