describe('DealerProfileDetailsResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealerProfileDetails');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

//    describe('save', function() {
//        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileDetailsResource, detailsSaveUrl) {
//            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
//            var expectedRequest = detailsSaveUrl;
//            var expectedMessage = 'UT response message';
//            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
//            var expectedResponse = {message: expectedMessage, objects: expectedDetails};
//
//            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
//            httpBackend.expectPOST(expectedRequest);
//
//            var promise = dealerProfileDetailsResource.save(details);
//
//            promise.then(function(actualResponse) {
//                expect(actualResponse.message).toEqual(expectedMessage);
//                for(var i = 0; i < actualResponse.objects.length; i++) {
//                    var returnedDetail = actualResponse.objects[i];
//                    var expectedDetail = expectedDetails[i];
//                    expect(returnedDetail.id).toEqual(expectedDetail.id);
//                    expect(returnedDetail.name).toEqual(expectedDetail.name);
////                }
////            });
//
//            httpBackend.flush();
//        }));
//    });

//    describe('submit', function() {
//        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileDetailsResource, detailsSubmitUrl) {
//            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
//            var expectedRequest = detailsSubmitUrl;
//            var expectedMessage = 'UT response message';
//            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
//            var expectedResponse = {message: expectedMessage, objects: expectedDetails};
//
//            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
//            httpBackend.expectPOST(expectedRequest);
//
//            var promise = dealerProfileDetailsResource.submit(details);
//
//            promise.then(function(actualResponse) {
//                expect(actualResponse.message).toEqual(expectedMessage);
//                for(var i = 0; i < actualResponse.objects.length; i++) {
//                    var returnedDetail = actualResponse.objects[i];
//                    var expectedDetail = expectedDetails[i];
//                    expect(returnedDetail.id).toEqual(expectedDetail.id);
//                    expect(returnedDetail.name).toEqual(expectedDetail.name);
////                }
////            });
////
////            httpBackend.flush();
////        }));
//    });
});