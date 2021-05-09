
// Create the flipbook
$('.flipbook').turn({
	// Elevation
	elevation: 50,
	// Enable gradients
	gradients: true,
	// Auto center this flipbook
	autoCenter: true,
	display:'single',
});

// toggle to show and hide the answer.
function toggleBox(box) {
	$(box).slideToggle(250);
	return false;
}

