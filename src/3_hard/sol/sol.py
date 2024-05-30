from scapy.all import rdpcap, TCP, UDP
import base64
import subprocess

def packet_callback(packet):
    # Check if the packet has a TCP or UDP layer and matches the port criteria
    if packet.haslayer(TCP) and (packet[TCP].sport == 6000 or packet[TCP].dport == 6000):
        payload = bytes(packet[TCP].payload)
        if len(payload) > 0:
            try:
                p = subprocess.Popen(['java', 'Main', payload.strip().decode()], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
                p.wait()
                if p.returncode == 0:
                    s = p.stdout.read()

                    if b'Excpetion' not in s:
                        print(s)

            except Exception as e:
                print(e)

# Read packets from the PCAP file
packets = rdpcap('../dist/conversation.pcap')
# Process each packet using the callback function
for packet in packets:
    packet_callback(packet)

