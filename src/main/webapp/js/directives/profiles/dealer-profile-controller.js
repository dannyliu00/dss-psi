(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder, $location, $modal, dealerSummaryPageUrl) {
        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(10)
            .withBootstrap();
        
        $scope.dealerEmail = "";
          
        $scope.sumActualValues = function() {
            var total = 0;
            for(var i = 0; i < $scope.orderSegments.length; i++) {
                var os = $scope.orderSegments[i];
                var osActual = angular.isNumber(os.actual) ? parseInt(os.actual) : 0;
                total = total + osActual;
                sumSegmentTotal(os.segmentName, os.id, osActual);
            }
            $scope.actualGrandTotal = total;
        	$scope.dirtyIndicator = $scope.dirtyIndicator + 1;
        };

        function sumSegmentTotal(segmentName, osId, newValue) {
            var segment = getSegment(segmentName);
            var total = 0;
            for(var i=0; i < segment.orderSegments.length; i++) {
                var orderSegment = segment.orderSegments[i];
                if(orderSegment.id === osId) {
                    orderSegment.actual = newValue;
                }
                total = total + parseInt(orderSegment.actual);
            }
            segment.actual = total;
        }

        $scope.autoFill = function(){
        	($scope.profile.type === "motorcycle" ? autoFillVic() : autoFillATV());
        	};
        
        function autoFillVic(){
            for(var i=0; i < $scope.orderSegments.length; i++) {
           	 $scope.orderSegments[i].actual = $scope.orderSegments[i].recommended;
            }
            $scope.sumActualValues();
       }
       
        function autoFillATV() { 
    	   for(var j=0; j < $scope.profile.periods.length; j++) {
               for(var i=0; i < $scope.orderSegments.length; i++) {
                   var orderSegment = $scope.orderSegments[i];
                   orderSegment.quantities[j].actual= orderSegment.quantities[j].recommended;
               		}
               }
           $scope.getActualGrandTotal();
       }
        
        function getSegment(name) {
            for(var i=0; i < $scope.segments.length; i++) {
                if($scope.segments[i].name === name) {
                    return $scope.segments[i];
                }
            }
        }

        $scope.toSummary = function(dealerId) {
            var changes = $scope.dirtyIndicator;

            switch(changes) {
                case 0:
                    var finalUrl = dealerSummaryPageUrl.replace(':dealerId', dealerId);
                    $location.path(finalUrl);
                    break;
                case 1:
                    var finalUrl = dealerSummaryPageUrl.replace(':dealerId', dealerId);
                    $location.path(finalUrl);
                    break;
                default:
                    openSaveDialog();
                    break;
            }
        };

        function openSaveDialog () {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/modal-template.html',
                controller: 'unsavedChangesController',
                size: 'sm'
//                resolve: {
//                    items: function (dealerId) {
//                        return dealerId;
//                    }
//                }
            });

            modalInstance.result.then(function (dealerId) {
                var finalUrl = dealerSummaryPageUrl.replace(':dealerId', $scope.dealer.dealerId);
                $location.path(finalUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }
        
//        $scope.displayDealerEmail = function() {
//        	console.log($scope.dealerEmail);
//        };
    }

    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();