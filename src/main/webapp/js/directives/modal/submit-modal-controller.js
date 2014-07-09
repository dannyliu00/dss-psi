(function() {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    function SubmitController($scope, $modalInstance, dealerProfileResource, orderSegments, profile, profileSubmitUrl) {
    	
        $scope.submit = function (os) {
        	
        	if(angular.element('input').hasClass('noncompliant')) {
	            profile.nonCompliant = true;
	        } else {
	            profile.nonCompliant = false;
	        }
	    	
	    	var os = {nonCompliant: profile.nonCompliant,orderSegments: orderSegments};
	    	
            dealerProfileResource.submit(os)
                .then(function(returnedos) {
                    $modalInstance.close();
                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    submitValues.SubmitController = SubmitController;
})();