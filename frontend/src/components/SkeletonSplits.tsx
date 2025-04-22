export default function SplitSkeletonCard() {
    return (
      <div className="animate-pulse p-4 rounded shadow-md bg-white space-y-4">
        <div className="h-6 bg-gray-300 rounded w-3/4"></div>
        <div className="h-4 bg-gray-200 rounded w-1/2"></div>
        <div className="h-3 bg-gray-200 rounded w-full"></div>
        <div className="h-3 bg-gray-200 rounded w-5/6"></div>
        <div className="h-3 bg-gray-200 rounded w-2/3"></div>
        <div className="h-10 bg-gray-300 rounded w-1/4 mt-4"></div>
      </div>
    );
  }
  