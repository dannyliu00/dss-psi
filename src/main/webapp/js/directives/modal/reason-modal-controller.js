(function() {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');
    
    function ReasonModalController($scope, $modalInstance, reasonCodeResource, appRoleResource, dealerProfileDetailsResource, data) {
    	
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
            for(var i=0; i<data.length; i++) {
                var item = data[i];
                item.dealerComments = this.reasonComments;
                item.reasonCode = id;
            }
            dealerProfileDetailsResource.submit(data).then(function() {
                $modalInstance.close(reasonCommentData);
            });
       };
    	
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');  
        };
    }

    reasonModal.ReasonModalController = ReasonModalController;
})();