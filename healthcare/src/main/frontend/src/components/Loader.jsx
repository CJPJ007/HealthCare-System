import React from "react";
import { DNA } from "react-loader-spinner";

export default function Loader() {
  return (
    <div className="absolute left-0 top-0 h-screen w-full flex items-center justify-center bg-gray-300/30 z-50">
      <DNA
        visible={true}
        height="80"
        width="80"
        ariaLabel="dna-loading"
        wrapperStyle={{}}
        wrapperClass="dna-wrapper"
      />
    </div>
  );
}
