import React from "react";
import { Link } from "react-router-dom";

export default function TaskBar() {
  return (
    <footer className="border-t-[1px] w-full flex justify-between p-2">
      <Link>
        <img src="/img/dashboard.png" alt="" className="h-6" />
      </Link>
      <Link>
        <img src="/img/favorites.png" alt="" className="h-6" />
      </Link>
      <Link>
        <img src="/img/user.png" alt="" className="h-6" />
      </Link>
    </footer>
  );
}
