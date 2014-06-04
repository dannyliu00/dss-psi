(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder) {
        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(10)
            .withBootstrap();

        $scope.sumActualValues = function() {
            var total = 0;
            for(var i = 0; i < $scope.orderSegments.length; i++) {
                var os = $scope.orderSegments[i];
                var osActual = angular.isNumber(os.actual) ? parseInt(os.actual) : 0;
                total = total + osActual;
                sumSegmentTotal(os.segmentName, os.id, osActual);
            }
            $scope.actualGrandTotal = total;
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

        function sumPeriodTotal(segmentName, osId, newValue) {
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

    }

    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();