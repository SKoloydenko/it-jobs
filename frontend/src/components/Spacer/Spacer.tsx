import React from "react";

export enum SpacerAxis {
	VERTICAL,
	HORIZONTAL,
}

interface SpacerProps {
	size: number;
	axis: SpacerAxis;
}

const Spacer: React.FC<SpacerProps> = ({ size, axis }) => {
	const width = axis === SpacerAxis.HORIZONTAL ? size : 0;
	const height = axis === SpacerAxis.VERTICAL ? size : 0;
	return (
		<span
			style={{
				display: "block",
				width,
				height,
			}}
		/>
	);
};

export default Spacer;
