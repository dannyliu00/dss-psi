(function() {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');
    
    function ReasonModalController($scope, $modalInstance,reasonCodeResource, appRoleResource) {
    	
    	$scope.reasonComments = "";
    	
    	var authorizationRoleId = 0;
    	
    	var reasonComments = "";
    	
    	appRoleResource.get().then(function(returnedRole){
    		authorizationRoleId = returnedRole.authorizationRoleId;
    		reasonCodeResource.get({roleId:authorizationRoleId})
    	.then(function(returnedReasonCodes) {
    	    $scope.reasonCodes = returnedReasonCodes;
    	    });
    	});
    	
        $scope.saveChanges = function (id) {
        	reasonComments = this.reasonComments;
            $modalInstance.close(id,reasonComments);
       };
    	
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');  
        };
    }

    reasonModal.ReasonModalController = ReasonModalController;
})();