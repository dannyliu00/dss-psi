describe('DealerProfileResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealerProfile');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('get', function() {
        it('returns a promise with a single profile of a specified dealer', inject(function(dealerProfileResource, profileUrl) {
            var profileId = 999;

            var profile = {profileId: profileId};
            var expectedRequest = profileUrl.replace(':profileId', profileId);
            var expectedProfile = { name: 'U.T. Victory Profile'};

            httpBackend.when('GET', expectedRequest).respond(expectedProfile);
            httpBackend.expectGET(expectedRequest);

            var promise = dealerProfileResource.get(profile);

            promise.then(function(actualProfile) {
                expect(actualProfile.name).toEqual(expectedProfile.name);
            });

            httpBackend.flush();
        }));
    });

});
