describe('DealerProfileDetailsResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealerProfileDetails');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    describe('save', function() {
        it('returns a promise with a list of details saved to the resource', inject(function(dealerProfileDetailsResource, detailsUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = detailsUrl;
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];

            httpBackend.when('POST', expectedRequest).respond(expectedDetails);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileDetailsResource.save(details);

            promise.then(function(returnedDetails) {
                expect(returnedDetails.length).toEqual(2);
                for(var i = 0; i < returnedDetails.length; i++) {
                    var returnedDetail = returnedDetails[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });

            httpBackend.flush();
        }));
    });
});