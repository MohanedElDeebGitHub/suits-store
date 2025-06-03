import os
import json

def find_java_files(root_dir):
    java_files = []
    for dirpath, _, filenames in os.walk(root_dir):
        for filename in filenames:
            if filename.endswith('.java'):
                full_path = os.path.join(dirpath, filename)
                java_files.append(full_path)
    return java_files

def collect_java_files_with_contents(root_dir):
    files_info = []
    java_files = find_java_files(root_dir)
    for file_path in java_files:
        rel_file = os.path.relpath(file_path, root_dir)
        rel_dir = os.path.relpath(os.path.dirname(file_path), root_dir)
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                contents = f.read()
        except Exception as e:
            contents = f"Error reading file: {e}"
        files_info.append({
            'file': rel_file,
            'directory': rel_dir,
            'contents': contents
        })
    return files_info

if __name__ == "__main__":
    import argparse
    parser = argparse.ArgumentParser(description="Collect all Java files with their contents and directories recursively")
    parser.add_argument("directory", help="Root directory to scan")
    parser.add_argument("--output", help="Output JSON file", default="java_files.json")
    args = parser.parse_args()

    data = collect_java_files_with_contents(args.directory)
    with open(args.output, 'w', encoding='utf-8') as f:
        json.dump(data, f, indent=2, ensure_ascii=False)
    print(f"Collected info on {len(data)} Java files. Output written to {args.output}")