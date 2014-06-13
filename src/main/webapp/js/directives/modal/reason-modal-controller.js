(function() {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');
    
    function ReasonModalController($scope, $modalInstance,reasonCodeResource, appRoleResource) {
    	
    	var authorizationRoleId = 0;
    	
    	var reasonCommentData = {};
    	
    	appRoleResource.get().then(function(returnedRole){
    		authorizationRoleId = returnedRole.authorizationRoleId;
    		reasonCodeResource.get({roleId:authorizationRoleId})
    	.then(function(returnedReasonCodes) {
    	    $scope.reasonCodes = returnedReasonCodes;
    	    });
    	});
    	
        $scope.saveChanges = function (id) {
        	reasonCommentData = {'comments':this.reasonComments,'id':id};
            $modalInstance.close(reasonCommentData);
       };
    	
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');  
        };
    }

    reasonModal.ReasonModalController = ReasonModalController;
})();