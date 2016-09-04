angular.module('auctionhsa.commons')
	.factory('popupService', ['$mdDialog', function($mdDialog) {
	
	return {
		showAlert : function(title, content) {
			$mdDialog.show( 
					$mdDialog.alert().parent(angular.element(document.querySelector('#popupContainer')))
					.clickOutsideToClose(true)
					.title(title)
					.textContent(content)
					.ariaLabel('Alert Dialog Demo')
					.theme('altTheme')
					.ok('Got it!')
					
			    );
		},
		
		showConfirm : function(title, content) {
			// Appending dialog to document.body to cover sidenav in docs app
			var confirm = $mdDialog.confirm()
			      .title(title)
			      .textContent(content)
			      .ariaLabel('Lucky day')
			      .ok('Yes')
			      .cancel('No');
			return $mdDialog.show(confirm);
		}
		
	};
}])