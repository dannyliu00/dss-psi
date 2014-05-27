describe('DealerProfilesResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealerProfiles');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('query', function() {
        it('returns a promise with a list of profiles of a specified dealer', inject(function(dealerProfilesResource, profilesUrl) {
            var dealerId = 123456;
            var dealer = {dealerId: dealerId};
            var expectedRequest = profilesUrl.replace(':dealerId', dealerId);
            var expectedList = [{name: 'U.T. Victory Profile'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);
            httpBackend.expectGET;

            var promise = dealerProfilesResource.query(dealer);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });
});

