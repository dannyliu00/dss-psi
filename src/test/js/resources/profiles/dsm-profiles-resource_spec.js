describe('DsmProfilesResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dsmProfiles');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('query', function() {
        it('returns a promise with a list of dealer profiles of a specified dsm', inject(function(dsmProfilesResource, dsmProfilesUrl) {
            var dsmId = 123456;
            var dsm = {dsmId: dsmId};
            var expectedRequest = dsmProfilesUrl.replace(':dsmId', dsmId);
            var expectedList = [{name: 'U.T. Victory Profile For A Dealer'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);
            httpBackend.expectGET;

            var promise = dsmProfilesResource.query(dsm);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });
});
